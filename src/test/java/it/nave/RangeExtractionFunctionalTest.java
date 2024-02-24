package it.nave;

public class RangeExtractionFunctionalTest extends RangeExtractionTest {
  @Override
  protected RangeExtraction getRangeExtraction() {
    return new RangeExtractionFunctional();
  }
}
