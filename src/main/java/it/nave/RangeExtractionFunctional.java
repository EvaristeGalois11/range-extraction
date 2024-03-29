package it.nave;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Gatherer;

/**
 * * Range Extraction * A format for expressing an ordered list of integers is to use a comma
 * separated list of either *
 *
 * <p>* individual integers * or a range of integers denoted by the starting integer separated from
 * the end integer in the range by a dash, '-'. The range includes all integers in the interval
 * including both endpoints. * It is not considered a range unless it spans at least 3 numbers. For
 * example "12,13,15-17" * Complete the solution so that it takes a list of integers in increasing
 * order and returns a correctly formatted string in the range format. *
 *
 * <p>* Example: *
 *
 * <p>* Solution.rangeExtraction(new int[] {-10, -9, -8, -6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10,
 * 11, 14, 15, 17, 18, 19, 20}) * # returns "-10--8,-6,-3-1,3-5,7-11,14,15,17-20" * Courtesy of
 * rosettacode.org
 */
public class RangeExtractionFunctional implements RangeExtraction {
  public String rangeExtraction(int[] arr) {
    return Arrays.stream(arr)
        .boxed()
        .gather(range())
        .map(this::rangeToString)
        .collect(Collectors.joining(","));
  }

  private Gatherer<Integer, ?, List<Integer>> range() {
    Gatherer.Integrator<List<Integer>, Integer, List<Integer>> integrator =
        (state, element, downstream) -> {
          if (!state.isEmpty()) {
            var last = state.getLast();
            if (element != last + 1) {
              downstream.push(List.copyOf(state));
              state.clear();
            }
          }
          return state.add(element);
        };
    BiConsumer<List<Integer>, Gatherer.Downstream<? super List<Integer>>> finisher =
        (o, downstream) -> {
          if (!o.isEmpty()) {
            downstream.push(o);
          }
        };
    return Gatherer.ofSequential(ArrayList::new, integrator, finisher);
  }

  private String rangeToString(List<Integer> range) {
    var result = range.getFirst().toString();
    if (range.size() > 1) {
      result += range.size() == 2 ? "," : "-";
      result += range.getLast();
    }
    return result;
  }
}
