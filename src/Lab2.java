import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Lab2 {
	String fileName;
	Set<Integer> finalStates;
	ArrayList<Map<Character, Set<Integer> > > transitions;
	boolean[] usedDfs;
	int startState, alphabetSize, statesSize, finalStatesSize;
	
	public Lab2(String inputFileName) {
		this.fileName = inputFileName;
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

        alphabetSize = scanner.nextInt();

        statesSize = scanner.nextInt();
        usedDfs = new boolean[statesSize];

        startState = scanner.nextInt();

        finalStatesSize = scanner.nextInt();
        finalStates = new HashSet<>();
        for (int i = 0; i < finalStatesSize; ++i) {
            finalStates.add(scanner.nextInt());
        }

        transitions = new ArrayList<>();
        for(int i = 0; i < statesSize; ++i) transitions.add(new HashMap<>());
        while (scanner.hasNext()) {
            Integer from = scanner.nextInt();
            Character letter = scanner.next().charAt(0);
            Integer to = scanner.nextInt();
            Set<Integer> letterTransitions = transitions.get(from).get(letter);
            if(letterTransitions == null) {
            	letterTransitions = new HashSet<>();
            	transitions.get(from).put(letter, letterTransitions);
            }
            letterTransitions.add(to);
        }
        scanner.close();
	}
	
	public Set<Integer> wordStates(String w0) {
		Set<Integer> states = new HashSet<>();
		states.add(startState);
		for(char letter : w0.toCharArray()) {
			Set<Integer> next_states = new HashSet<>();
			for(Integer state : states) {
				if(transitions.get(state).keySet().contains(letter)) {
					for(Integer toState : transitions.get(state).get(letter)) {
						next_states.add(toState);
					}
				}
			}
			states = next_states;
		}
		return states;
	}
	
	public void dfs(int state, String w1, Set<String> w1Words) {
		usedDfs[state] = true;
		if(finalStates.contains(state)) w1Words.add(w1);
		for(Character letter : transitions.get(state).keySet()) {
			for(Integer toState : transitions.get(state).get(letter)) {
				if(!usedDfs[toState])
					dfs(toState, w1 + letter, w1Words);
			}
		}
		usedDfs[state] = false;
	}
	
	public void runDfs(int state, Set<String> w1Words) {
		for(int i = 0; i < statesSize; ++i) usedDfs[i] = false;
		dfs(state, "", w1Words);
	}
	
	public Set<String> solve(String w0) {
		Set<String> w1Words = new HashSet<>();
		for(Integer state : wordStates(w0)) {
			runDfs(state, w1Words);
		}
		return w1Words;
	}
	
	
}
