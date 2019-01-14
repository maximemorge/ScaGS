// Copyright (C) Maxime Morge 2019

/*
 * Class representing a woman in a SMP with name
 */
class Woman(override val name : String) extends Individual(name){

  /**
    * Alternative constructor
    */
  def this(name : String, pref : Array[String])= {
    this(name)
    preferences=pref
    preferences.foreach{ partner => marks += (partner -> false)}
    preferences.foreach{ partner => deleted += (partner -> false)}
  }
}
