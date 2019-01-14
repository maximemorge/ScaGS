// Copyright (C) Maxime Morge 2014

/*
 * @author Maxime Morge
 * @version 2014-04-10
 * Class representing a Stable Matching Problem
 * with n men and women
 */
class SMP(val n: Int, val men: Set[Man], val women: Set[Woman]){
  
  //Return the man corresponding to a name
  def getMan(name: String) : Man = {
    men.find(man => man.name== name) match {
      case Some(s) => s
      case None => throw new RuntimeException("The man "+name+" is not found")
    }
  }
  
  //Return the woman corresponding to a name
  def getWoman(name: String) : Woman = {
    women.find(woman => woman.name== name) match {
      case Some(s) => s
      case None => throw new RuntimeException("The woman "+name+" is not found")
    }
  }
  
  //Return the individual corresponding to a name
  def getIndividual(name: String) : Individual = {
    (men++women).find(ind => ind.name== name) match {
      case Some(s) => s
      case None => throw new RuntimeException("The ind "+name+" is not found")
    }
  }
  
  
  //Compute the utility of n men
  def maleWelfare(solution: Matching): Double = {
    var swf=0.0;
    for (man <- men){
      swf+=man.utility(solution.getWife(man).name,n);
    }
    swf/n
  }

  //Compute the utility of n women
  def femaleWelfare(solution: Matching): Double = {
    var swf=0.0;
    for (woman <- women){
      swf+=woman.utility(solution.getHusband(woman).name,n);
    }
    swf/n
  }
  
  //Compute the diff utility of women
  def utilitarianWelfare(solution: Matching): Double = (maleWelfare(solution) + femaleWelfare(solution))/2

  //Compute the utility of individuals (n women+ n men)
  def egalitarianWelfare(solution: Matching): Double = 1 - (maleWelfare(solution) - femaleWelfare(solution)).abs

  //Compute the utility of individuals (n women+ n men)
  def conjugalWelfare(solution: Matching): Double = {
    var diff=0.0
    for (m <- solution.marriages){
      diff+= (m.husband.utility(m.wife.name,n) - m.wife.utility(m.husband.name,n)).abs
    }
    1 - (diff/n.toDouble)
  }

  //Compare two individuals based on their name
  def comparator (ind1: Individual, ind2: Individual) = (ind1.name compareToIgnoreCase ind2.name) < 0

  //Nice print of an solution
  override def toString(): String ={
    var s : String = "SMP of size: "+n+"\n"
    s+="men: \n"
    for (man <-men.toList.sortWith(comparator)){
      s+=man
    }
    s+="women: \n"
    for (woman <-women.toList.sortWith(comparator)){
      s+=woman
    }
    return s
  }
  //In order to backup the SMP
  def backup(): Unit = {
    for (man <-men){
      man.backup()
    }
    for (woman <-women){
      woman.backup()
    }
  }
  
  //In order to restore the SMP
  def restore(): Unit = {
    for (man <-men){
      man.restore()
    }
    for (woman <-women){
      woman.restore()
    }
  }
  
  //Remove an individaul in the preference lists of the partner
  def remove(ind: Individual): Unit ={
    for (ind <- (men++women)) ind.remove(ind.name)
  }
}
