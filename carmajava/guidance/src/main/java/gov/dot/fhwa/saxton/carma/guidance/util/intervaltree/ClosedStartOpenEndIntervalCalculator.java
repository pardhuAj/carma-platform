/*
 * Copyright (C) 2018 LEIDOS.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package gov.dot.fhwa.saxton.carma.guidance.util.intervaltree;

public class ClosedStartOpenEndIntervalCalculator<T> implements IntervalCalculatorStrategy<T> {

	// Just make this package private
	protected ClosedStartOpenEndIntervalCalculator() {}

	@Override
	public boolean checkIntersection(Interval<T> inter1, Interval<T> inter2) {
        // Rename values to reuse previous code
        double start1 = inter1.getStart();
        double end1 = inter1.getEnd();
        double start2 = inter2.getStart();
        double end2 = inter2.getEnd();

        // Analyze case by case

        // Start2 is inside the first range
		if (start2 >= start1 && start2 < end1) {
			return true;
		}
		// End2 is inside the range
		if (end2 > start1 && end2 < end1) { 
			return true;
		}
		// The whole of range 2 overlaps range1
		if (start2 <= start1 && end2 >= end1) {
			return true;
		}

		// Start2 is inside the first range
		if (start1 >= start2 && start1 < end2) {
			return true;
		}
		// End2 is inside the range
		if (end1 > start2 && end1 < end2) { 
			return true;
		}
		// The whole of range 2 overlaps range1
		if (start1 <= start2 && end1 >= end2) {
			return true;
		}

		return false; // No intersection detected
	}

	@Override
	public boolean checkIntersection(Interval<T> inter, double pt) {
		return pt >= inter.getStart() && pt < inter.getEnd();
	}

	@Override
	public int compareIntervals(Interval<T> inter1, Interval<T> inter2) {
		if (checkIntersection(inter1, inter2)) {
			return 0;
		} else if (inter1.getEnd() < inter2.getStart()) {
				return -1;
		} else {
			return 1;
		}
	}

	@Override
	public int compareIntervalAndPoint(Interval<T> inter1, double pt) {
		return 0;
	}

}