package example

class Greeter(greeting: String) {

  def greet(person: Person): String = {
    val n = person.name
    if (n.isBlank) {
      throw new IllegalArgumentException("person name is blank")
    }
    s"${greeting} ${person.name}"
  }

}
