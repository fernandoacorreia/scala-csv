package example

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class GreeterTest extends AnyFlatSpec with Matchers {

  trait Fixture {
    lazy val greetingMessage = "Hello"
    lazy val greeter = new Greeter(greetingMessage)
    lazy val personName = "John"
    lazy val person = Person(personName)
    lazy val expectedGreeting = s"$greetingMessage $personName"
  }

  "greet" should "greet the person with the provided greeting" in new Fixture {
    val result = greeter.greet(Person(personName))
    result should be(expectedGreeting)
  }

  it should "throw an exception if the name is blank or empty" in new Fixture {
    override lazy val personName: String = ""
    intercept[IllegalArgumentException] {
      greeter.greet(person)
    }
  }

}
