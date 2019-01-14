// Copyright (C) Maxime Morge 2019

/**
 * Trait solving a SMP
 */
trait Solver {
	var terminated = false

	/**
		* Runs solver
		*/
	def run() : Unit

	/**
		* Resets solver
		*/
	def reset() : Unit

	/**
		* Solves = reset + solve
		*/
	def solve() : Unit = {
	  reset()
	  run()
	}
}
