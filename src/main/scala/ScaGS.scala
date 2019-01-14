// Copyright (C) Maxime Morge 2019

/**
  * Main app
  */
object ScaGS{
  def main(args: Array[String]) {
    if (args.length == 0){
      println("Usage: java -jar ScaGS.jar filename")
      sys.exit(-1)
    }
    val readFile = new ReadFile()
    val smp =  readFile.read(args(0))
    println(smp)
    val mSolver= new GSSolver(smp)
    mSolver.orientation=mSolver.MEN_ORIENTED
    mSolver.solve()//SMP with men-oriented Gale-Shapley algorithm
    println(mSolver.print())
    sys.exit(0)
  }
}
