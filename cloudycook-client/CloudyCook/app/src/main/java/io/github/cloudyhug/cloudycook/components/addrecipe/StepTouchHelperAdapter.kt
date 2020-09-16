package io.github.cloudyhug.cloudycook.components.addrecipe

interface StepTouchHelperAdapter {
  fun onStepMove(fromPosition: Int, toPosition: Int)
  fun onStepSwiped(position: Int)
}