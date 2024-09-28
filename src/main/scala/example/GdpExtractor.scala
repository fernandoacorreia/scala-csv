package example

case class CountryGdpData(
    countryName: String,
    gdp: Float
)

class GdpExtractor(dataFileName: String) {
  def extract(): Seq[CountryGdpData] = List.empty
}
