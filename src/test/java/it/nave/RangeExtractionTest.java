package it.nave;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RangeExtractionTest {
  private static final int[] TEST_CASE =
      new int[] {
        -10, -9, -8, -6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20
      };
  private static final String EXPECTED = "-10--8,-6,-3-1,3-5,7-11,14,15,17-20";

  @Test
  void rangeExtraction() {
    var result = RangeExtraction.rangeExtraction(TEST_CASE);
    assertEquals(EXPECTED, result);
  }
}
