// Copyright (C) Maxime Morge 2019

/**
 * Class solving SMP with Gale-Shapley algorithm
 */
class GSSolver(pb: SMP) extends SMPSolver(pb) with Solver {

  /**
    * Runs solver
    */
  def run(): Unit = {
    var proposers: List[Individual] = List[Individual]()
    if (orientation == MEN_ORIENTED) proposers = smp.men.toList
    else proposers = smp.women.toList
    //While there is a proposer who is free and not desperate
    while (proposers.nonEmpty) {
      if (debug) println("There is still a proposer who is free and not desperate")
      for (proposer <- proposers) {
        if (proposer.free && (proposer.concessionLevel < proposer.preferences.length)) {
          val disposer: Individual = smp.getIndividual(proposer.target)
          if (debug) println(proposer.name + " proposes to " + disposer.name)
          val cuckold: Individual = solution.partner(disposer)
          if (debug) println("The current partner of " + disposer.name + " is " + cuckold.name)
          if (disposer.prefers(proposer.name, cuckold.name)) {
            if (debug) println(proposer.name + " and " + disposer.name + " get married")
            proposer.free = false
            disposer.free = false
            solution = solution.add(proposer, disposer)
            proposers = proposers.filterNot(p => p == proposer)
            if (!cuckold.isPhantom) {
              if (debug) println(cuckold.name + " and " + disposer.name + " get divorced")
              solution = solution.remove(disposer, cuckold)
              if (debug) println(cuckold.name + " is free")
              cuckold.free = true
              proposers = cuckold :: proposers
            }
             // Eventually
             //disposer.preferences=removeSuccessors(disposer,proposer.name,disposer.preferences)
          } else {
            // Go to the next partner
            if (debug) println(disposer.name + " refuses " + proposer.name)
            if (debug) println(proposer.name + " concedes ")
            proposer.concessionLevel += 1
          }
        } //Go to the next partner
        else proposers = proposers.filterNot(p => p == proposer)
      }
    }
    if (debug) println("That's all folk !")
    terminated = true
  }

  /**
    * Removes successors of partner in the preference list of an individual and reciprocally
    */
  def removeSuccessors(ind: Individual, partner: String, preferences: List[String]): List[String] = {
    if (preferences.isEmpty) return Nil
    if (preferences.head != partner) return preferences.head :: removeSuccessors(ind, partner, preferences.tail)
    else {
      List(preferences.head)
    }
  }
}
