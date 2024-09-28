package example

class GdpSummarizer(maxNumber: Int) {
  def summarize(input: Seq[CountryGdpData]): List[CountryGdpData] = {
    val sorted = input.sortBy(_.gdp * -1)
    sorted.take(maxNumber).toList
  }
}
