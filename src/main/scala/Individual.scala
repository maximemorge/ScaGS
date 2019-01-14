// Copyright (C) Maxime Morge 2019

/**
 * Class representing an individual in a SMP with a name
 */
class Individual(val name : String){

  /**
    * Is the individual free or married ?
    */
  var free=true

  /**
    * Preferences of the individual
    */
  var preferences : Array[String] = Array[String]()

  /**
    * Mark the potential partners
    */
  var marks : Map[String,Boolean]= Map[String,Boolean]()

  /**
    * Deleted potential partners
    */
  var deleted : Map[String,Boolean] = Map[String,Boolean]()

  /**
    * Preferences of the individual for backup
    */
  var backupPreferences : Array[String] = Array[String]()

  /**
    * Concession level
    */
  var concessionLevel = 0

  /**
    * Alternative constructor
    */
  def this(n : String, pref : Array[String])={
    this(n)
    preferences=pref
    preferences.foreach{ partner => marks += (partner -> false)}
    preferences.foreach{ partner => deleted += (partner -> false)}
  }

  /**
    * Prints nicely an individual
    */
  override def toString: String={
    name+" ["+
    (for(partner<-preferences) yield partner+" ").mkString(" ")+
     "]\n"
  }

  /**
    * Overrides the equals method
    */
  override def equals(obj: Any): Boolean =
    obj.isInstanceOf[Individual] && obj.asInstanceOf[Individual].name==name

  /**
    * Returns true if the individual is the phantom
    */
  def isPhantom: Boolean = this.name == "phantom"

  /**
    * Returns true if a potential partners belongs to the preference list
    */
  def isRational(potentialPartner: String) : Boolean = preferences.contains( potentialPartner)

  /**
    * Returns
    * true if a and b are rational and a is preferred to b
    * false if b is rational and a is not rational
    * false if a and b are irrational
    */
  def prefers(a: String, b: String): Boolean ={
    for (ind <- preferences){
      if (ind==a) return true
      if (ind==b) return false
    }
    false
  }

  /**
    * Targets a potential partner according to the concession level
    */
  def target: String = preferences(concessionLevel)

  /**
    * Returns the regret of the individual with a potential partner,
    * 0 with the first partner in the list,
    * 1 with the second partner in the list,
    * etc.
    */
  def regret(potentialPartner: String) : Int = {
    val position= preferences.indexOf(potentialPartner)
    if (position==(-1)) preferences.length
    else position
  }

  /**
    * Returns the regret of the individual in a matching
    */
  def regret(matching: Matching) : Int = regret(matching.partner(this).name)


  /**
    * Returns the utility of an individual
    * The more regret is, the less the utility is
    *  The utility of an irrational partner is 0
    */
  def utility(partner: String, nbOfPotentialPartners: Int) : Double = {
    if (isRational(partner))
      ((nbOfPotentialPartners-1)-regret(partner)).toDouble/(nbOfPotentialPartners-1)
    else 0.0
  }

  /**
    * Removes an individual in the preference list
    */
  def remove(partner : String) : Unit ={
    preferences=preferences.filterNot(p => p==partner)
  }

  /**
    * Backups the preferences/status
    */
  def backup(): Unit = {
    backupPreferences=preferences.map{partner => partner}
  }

  /**
    * Restores the preferences/status
    */
  def restore(): Unit = {
    preferences=backupPreferences.map{partner => partner}
    free=true
    concessionLevel=0
  }
}
