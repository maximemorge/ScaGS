// Copyright (C) Maxime Morge 2019

/**
 * Class representing a marriage in a SMP with a husband and a wife
 */
class Marriage(val husband: Man, val wife: Woman){

  /**
    * Additional constructor
    */
  def this(wife: Woman, husband: Man)=this(husband,wife)

  /**
    * Override the equals method
    */
  override def equals(obj: Any): Boolean = obj.isInstanceOf[Marriage] &&
  obj.asInstanceOf[Marriage].husband==husband &&
  obj.asInstanceOf[Marriage].wife==wife

  /**
    * Print nicely a marriage
    */
  override def toString: String = husband.name+":"+wife.name
}
