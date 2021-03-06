package xtend.tutorial.basics

import xtend.tutorial.util.Person
import junit.framework.TestCase

class Xtend08_RichStrings extends TestCase {
	
	def void testRichStrings() {
		println(new Person("Joe", "Developer").writeLetterTo)
	}
	
	/**
	 * Rich strings are a special feature for readable code concatenation.
	 * It supports a unique automatic indentation handling, which is also refelected tooling-wise
	 * Note the indentation before the call to signature.
	 */
	def writeLetterTo(Person p) '''
		Dear �p.forename�,
		
		bla bla foo
		
		Yours sincerely,
		
		Joe Developer
		
			�signature�
	'''
	
	def signature() '''
		Bla bla Foo Bar
		(c) 2011 - all rights reserved
		 - note the multiline, and the correct indentation -
	'''
	
}