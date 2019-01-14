// Copyright (C) Maxime Morge 2019

/**
 * Abstract class solving a SMP
 */
abstract class SMPSolver(val smp: SMP) {
  this:Solver=>
  var debug = false
  
  val MEN_ORIENTED : Int = 0
  val WOMEN_ORIENTED : Int = 1

  var orientation : Int = MEN_ORIENTED

  var solution : Matching = new Matching(Nil)

  /**
    * Resets SMP , i.e. concession +free
    */
  def reset(): Unit ={
    for (individual <- smp.men++smp.women){
      individual.concessionLevel=0
      individual.free=true
    }
  }

  /**
    * Print nicely the outcome
    */
  def print(): String = {this.getClass.getName+"\n"+
    "orientation: "+( if (orientation==this.MEN_ORIENTED) "men-oriented" else "women-oriented" )+"\n"+
    solution.toString+"\n"
  }
}
