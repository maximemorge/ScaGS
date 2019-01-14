// Copyright (C) Maxime Morge 2019

/**
 * Class representing a man in a SMP with name
 */
class Man(override val name : String) extends Individual(name){

  /**
    * Alternative constructor
    */
  def this(name : String, pref : Array[String])={
    this(name)
    preferences=pref
    preferences.foreach{ partner => marks += (partner -> false)}
    preferences.foreach{ partner => deleted += (partner -> false)}
  }
}
