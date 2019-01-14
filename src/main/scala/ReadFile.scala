// Copyright (C) Maxime Morge 2019
import scala.io.Source
import scala.collection.immutable.HashSet

/**
  * Class reading a file to build a SMP
  */
class ReadFile {

  /**
    * Returns the SMP for the filename
    */
  def read(filename : String) : SMP = {
    var men=new HashSet[Man]()
    var women=new HashSet[Woman]()
    var n=0
    for(line <- Source.fromFile(filename).getLines()){
      val noSpaceLine = line.replaceAll("\\s", "")
      var lineClean=noSpaceLine.replaceAll("""\]\)\.""", "")
      lineClean=lineClean.replaceAll("""[\p{Punct}]+""", ":")
      println(lineClean)
      val tab = lineClean.split(":")
      n=tab.length-1
      tab(0) match{
        case "men" =>
          for (i <- 1 until tab.length){
            men += new Man(tab(i))
          }
        case "women" =>
          for (i <- 1 until tab.length){
            women += new Woman(tab(i))
          }
        case "preference"=>
          val name=tab(1)
          for(x <- men.asInstanceOf[Set[Individual]] | women.asInstanceOf[Set[Individual]]){
            if (x.name == name){
              x.preferences=tab.slice(2, n+1)
            }
          }
        case _ => // Ignore other lines
      }
    }
    new SMP(n, men.asInstanceOf[Set[Man]], women.asInstanceOf[Set[Woman]])
  }
}
