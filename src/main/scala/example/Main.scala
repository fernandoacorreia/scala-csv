package example

object Main {

  def main(args: Array[String]): Unit = {
    val dataFileName = "data/API_NY.GDP.MKTP.CD_DS2_en_csv_v2_31795.csv"
    val gdpExtractor = new GdpExtractor(dataFileName)
    val countryGdpData = gdpExtractor.extract()
    val gdpSummarizer = new GdpSummarizer()
    val summarizedGdp = gdpSummarizer.summarize(countryGdpData)
    val gdpSummaryPrinter = new GdpSummaryPrinter()
    gdpSummaryPrinter.print(summarizedGdp)
  }

}
