package it.nave;

public class RangeExtractionImperativeTest extends RangeExtractionTest {
  @Override
  protected RangeExtraction getRangeExtraction() {
    return new RangeExtractionImperative();
  }
}
