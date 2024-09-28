package example

import java.io.FileInputStream

object Main {

  def main(args: Array[String]): Unit = {
    // Read and parse input data.
    val dataFileName = "data/API_NY.GDP.MKTP.CD_DS2_en_csv_v2_31795.csv"
    val is = new FileInputStream(dataFileName)
    val parsedData = try {
      val gdpExtractor = new GdpExtractor()
      gdpExtractor.extract(is)
    } finally {
      is.close()
    }

    // Extract countries only
    val selector = new CountrySelector
    val countryData = selector.selectCountriesOnly(parsedData)

    // Summarize GDP per country, sorting and selecting the largest GDPs.
    val gdpSummarizer = new GdpSummarizer(maxNumber = 20)
    val summarizedGdp = gdpSummarizer.summarize(countryData)

    // Format and print out the largest GDPs per country.
    val gdpSummaryPrinter = new GdpSummaryPrinter()
    gdpSummaryPrinter.print(summarizedGdp)
  }
}
