package example

class GdpSummaryPrinter() {
  def print(summarizedGdp: Seq[CountryGdpData]): Unit = {
    val nameContentWidth = summarizedGdp.map(_.countryName).map(_.length).max
    val gdpContentWidth = 23

    printSeparator(nameContentWidth, gdpContentWidth)
    println(s"| ${rightPad("Country", nameContentWidth)} | ${leftPad("GDP (USs)", gdpContentWidth)} |")
    printSeparator(nameContentWidth, gdpContentWidth)
    summarizedGdp.foreach { data =>
      println(
        s"| ${rightPad(data.countryName, nameContentWidth)} | ${leftPad(formattedGdp(data.gdp), gdpContentWidth)} |"
      )
    }
    printSeparator(nameContentWidth, gdpContentWidth)
  }

  private def printSeparator(nameContentWidth: Int, gdpContentWidth: Int): Unit = {
    println(s"+${"-" * (nameContentWidth + 2)}+${"-" * (gdpContentWidth + 2)}+")
  }

  private def leftPad(s: String, w: Int): String = {
    val padding = " " * (w - s.length)
    s"$padding$s"
  }

  private def rightPad(s: String, w: Int): String = {
    val padding = " " * (w - s.length)
    s"$s$padding"
  }

  def formattedGdp(gdp: Double): String = {
    f"$$${gdp}%,.2f"
  }
}
