//   Copyright 2012-2013 Fraunhofer FOKUS
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.
package de.fraunhofer.fokus.fuzzing.fuzzino.heuristics.generators.string;

import java.util.List;

import de.fraunhofer.fokus.fuzzing.fuzzino.heuristics.ComputableFuzzingHeuristic;
import de.fraunhofer.fokus.fuzzing.fuzzino.heuristics.generators.string.data.SQLInjections;
import de.fraunhofer.fokus.fuzzing.fuzzino.request.StringSpecification;
import de.fraunhofer.fokus.fuzzing.fuzzino.request.StringType;

/**
 * This generator creates some strings having the capability to reveal SQL
 * injection weaknesses. It is taken from Sulley.
 */
public class SQLInjectionsGenerator extends SimpleStringGenerator {

	private static final long serialVersionUID = -7843381710363032951L;
	protected static SQLInjections sqlInjections = SQLInjections.INSTANCE;

	public SQLInjectionsGenerator(StringSpecification stringSpec, long seed) {
		super(stringSpec, seed);
	}

	public SQLInjectionsGenerator(long seed, StringSpecification stringSpec, ComputableFuzzingHeuristic<?> owner) {
		super(stringSpec, seed, owner);
	}

	@Override
	public boolean canCreateValuesFor(StringSpecification stringSpec) {
		boolean validType = stringSpec.getType() == StringType.SQL;

		return validType;
	}

	@Override
	public String getName() {
		return "SQLInjections";
	}

	@Override
	public List<String> getFuzzValues() {
		return sqlInjections.getValues();
	}

	@Override
	public List<Integer> getFuzzValueLengths() {
		return sqlInjections.getLengths();
	}

}
