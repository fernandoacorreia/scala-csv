package example

object Main {

  def main(args: Array[String]): Unit = {
    val name = if (args.length >= 1) {
      args(0)
    } else {
      "world"
    }
    val p = Person(name)
    val greeting = "Hello, "
    val g = new Greeter(greeting)
    println(g.greet(p))
  }

}
