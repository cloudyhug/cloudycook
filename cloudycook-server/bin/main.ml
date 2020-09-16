open Core
open Async
open Httpaf
open Httpaf_async

let file_content = ref Bigstringaf.empty

let request_handler _ reqd =
  match Reqd.request reqd with
  | { Request.meth = `GET; _ } ->
    let headers = Headers.of_list [ ("content-type", "application/json"); ("connection", "close") ] in
    Reqd.respond_with_bigstring reqd (Response.create ~headers `OK) !file_content
  | _ ->
    let headers = Headers.of_list [ ("connection", "close") ] in
    Reqd.respond_with_string reqd (Response.create ~headers `Method_not_allowed) ""

let error_handler _ ?request:_ error start_response =
  let response_body = start_response Headers.empty in
  begin match error with
  | `Exn exn ->
    Body.write_string response_body (Exn.to_string exn);
    Body.write_string response_body "\n";
  | #Status.standard as error ->
    Body.write_string response_body (Status.default_reason_phrase error)
  end;
  Body.close_writer response_body

let main port filename () =
  let content = Stdio.In_channel.read_all filename in
  file_content := Bigstringaf.of_string ~off:0 ~len:(String.length content) content;
  let where_to_listen = Tcp.Where_to_listen.of_port port in
  Tcp.Server.create_sock ~on_handler_error:`Raise where_to_listen
    (Server.create_connection_handler ~request_handler ~error_handler)
  >>= fun _server -> Deferred.never ()

let () =
  Command.async
    ~summary:"Serveur HTTP de CloudyCook"
    Command.Param.(
      map
        (both
          (flag "-p" (optional_with_default 8080 int) ~doc:"int Port sur lequel écouter")
          (flag "-i" (required string) ~doc:"string Nom de fichier de recettes"))
        ~f:(fun (port, filename) ->
              (fun () -> main port filename ())))
  |> Command.run