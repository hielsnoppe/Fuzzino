import de.fraunhofer.fokus.fuzzing.fuzzino.FuzzedValue;
import de.fraunhofer.fokus.fuzzing.fuzzino.heuristics.generators.IntegerGenerator;
import de.fraunhofer.fokus.fuzzing.fuzzino.heuristics.generators.StringGenerator;
import de.fraunhofer.fokus.fuzzing.fuzzino.heuristics.generators.number.BadFloatGenerator;
import de.fraunhofer.fokus.fuzzing.fuzzino.heuristics.generators.number.BoundaryNumbersGenerator;
import de.fraunhofer.fokus.fuzzing.fuzzino.heuristics.generators.string.BadFilenamesGenerator;
import de.fraunhofer.fokus.fuzzing.fuzzino.request.IntegerSpecification;
import de.fraunhofer.fokus.fuzzing.fuzzino.request.NumberSpecification;
import de.fraunhofer.fokus.fuzzing.fuzzino.request.RequestFactory;
import de.fraunhofer.fokus.fuzzing.fuzzino.request.StringEncoding;
import de.fraunhofer.fokus.fuzzing.fuzzino.request.StringSpecification;
import de.fraunhofer.fokus.fuzzing.fuzzino.request.StringType;

public class JavaBasicExample {

	public static void main(String[] args) {
		
		// Integer/Long
		
		for (FuzzedValue<Long> fuzzedValue : boundaryNumbers(0l, 10l)) {
			System.out.println(fuzzedValue);
		}
		
		// Float
		
		for (FuzzedValue<Double> fuzzedValue : badFloats(0.0, 10.0)) {
			System.out.println(fuzzedValue);
		}
		
		// Strings

		int i = 0, max = 9;
		
		for (FuzzedValue<String> fuzzedValue : badFilenames()) {
			System.out.println(fuzzedValue);
		
			if (++i > max) break;
		}
	}
	
	public static IntegerGenerator boundaryNumbers (Long lowerBound, Long upperBound) {
		
		IntegerSpecification spec = RequestFactory.INSTANCE.createNumberSpecification();
		spec.setMin(lowerBound);
		spec.setMax(upperBound);
		
		return new BoundaryNumbersGenerator(spec, 0);
	}
	
	public static BadFloatGenerator badFloats (Double lowerBound, Double upperBound) {
		
		NumberSpecification<Double> spec = RequestFactory.INSTANCE.createFloatSpecification();
		spec.setMin(lowerBound);
		spec.setMax(upperBound);
		
		return new BadFloatGenerator(spec, 0);
	}
	
	public static StringGenerator badFilenames () {
		
		StringSpecification spec = RequestFactory.INSTANCE.createStringSpecification();
		spec.setEncoding(StringEncoding.UTF8);
		spec.setType(StringType.FILENAME);
		spec.setIgnoreLengths(true);

		return new BadFilenamesGenerator(spec, 0);
	}
}
