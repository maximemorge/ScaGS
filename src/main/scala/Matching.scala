// Copyright (C) Maxime Morge 2019

/**
  * Class representing an immutable matching in a SMP
  */
class Matching(val marriages: List[Marriage]){

  /**
    * Compares two marriages based on the husband name
    */
  def comparator (m1: Marriage, m2: Marriage) : Boolean =
    (m1.husband.name compareToIgnoreCase m2.husband.name) < 0

  /**
    * Prints nicley a marriage
    */
  override def toString: String = {
    val sortedMarriages=marriages.sortWith(comparator)
    var s: String=""
    for (m<-sortedMarriages){
      s+=m+"\n"
    }
    s
  }

  /**
    * Returns the partner of an individual
    */
  def partner(ind : Individual) : Individual = {
    for (m<-marriages){
      if (m.husband==ind) return m.wife
      if (m.wife==ind) return m.husband
    }
    new Individual("phantom",Array())
  }

  /**
    * Returns the husband of a woman
    */
  def getHusband(ind : Woman) : Man = {
    for (m<-marriages){
      if (m.wife==ind) return m.husband
    }
    new Man("phantom",Array())
  }

  /**
    * Returns the husband of a woman
    */
  def getWife(ind : Man) : Woman = {
    for (m<-marriages){
      if (m.husband==ind) return m.wife
    }
    new Woman("phantom",Array())
  }

  /**
    * Adds a marriage
    */
  def add(m: Marriage) : Matching = new Matching(m :: marriages)

  /**
    * Adds a couple
    */
  def add(a: Individual, b: Individual) : Matching = {
    a match {
      case _: Man => add(new Marriage(a.asInstanceOf[Man], b.asInstanceOf[Woman]))
      case _: Woman => add(new Marriage(b.asInstanceOf[Man], a.asInstanceOf[Woman]))
    }
  }

  /**
    * Removes a marriage
    */
  def remove(m: Marriage) : Matching = new Matching(marriages.filterNot(elm => elm == m ))

  /**
    * Removes a couple
    */
  def remove(a: Individual, b: Individual) : Matching ={
    a match {
      case _ : Man => remove(new Marriage(a.asInstanceOf[Man],b.asInstanceOf[Woman]))
      case _ : Woman => remove(new Marriage(b.asInstanceOf[Man],a.asInstanceOf[Woman]))
    }
  }

  /**
    * Returns true if the matching is unstable
    */
  def isUnstable : Boolean ={
    for(marriage <- marriages){
      val man = marriage.husband
      val woman = marriage.wife
      val (_,marriages2)=marriages.splitAt(marriages.indexOf(marriage)+1)
      for(marriage2 <- marriages2){
        val man2 = marriage2.husband
        val woman2 = marriage2.wife
        if (man.prefers(woman2.name,woman.name) && woman2.prefers(man.name,man2.name)){
          println("blocking pair: "+man.name+"--"+woman2.name)
          return true
        }
        if (woman.prefers(man2.name,man.name) && man2.prefers(woman.name,woman2.name)){
          println("blocking pair: "+woman.name+"--"+man2.name)
          return true
        }
      }
    }
    false
  }
}
