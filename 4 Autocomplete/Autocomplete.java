/*
THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING ANY
SOURCES OUTSIDE OF THOSE APPROVED BY THE INSTRUCTOR. ETHAN YANG
*/

import java.util.*;
public class Autocomplete extends AbstractAutocomplete<List<String>>{
    private LinkedList<String> candidates = new LinkedList<>();

    public Autocomplete(String dict_path, int max){
        super(dict_path, max);
    }

    public List<String> getCandidates(String prefix){
        List<String> result = new LinkedList<>();
        TrieNode<List<String>> node = find(prefix);

        // If prefix is not in trie, return null
        if (node == null) {
            return null;
        }

        // Record all the candidates that were picked
        List<String> pickedCandidates = new LinkedList<>();
        for (String candidate : candidates) {
            if (candidate.startsWith(prefix)) {
                pickedCandidates.add(candidate);
            }
        }

        // Add words to the result list
        addCandidates(node, prefix, result);

        // Sort the list by length, then by alphabetic order
        result.sort((a, b) -> {
        if (a.length() != b.length()){
            return a.length() - b.length();
        }
        else{
            return a.compareTo(b);
        }
    	});

        // Remove the duplicates
        for (String picked : pickedCandidates) {
            result.remove(picked);
        }
        // Add the picked candidates recorded above to the result list
        result.addAll(0, pickedCandidates);

        // Trim the list to the maximum number of candidates
        return result.subList(0, Math.min(result.size(), getMax()));
    }

    public void pickCandidate(String prefix, String candidate){
        // Put the picked candidate to the first index of the list
        // Remove the duplicate
        candidates.remove(candidate);
        candidates.addFirst(candidate);
    }

    private void addCandidates(TrieNode<List<String>> node, String prefix, List<String> result){
        // If the current node makes up a complete word, add the prefix
        if (node.isEndState()) {
            result.add(prefix);
        }

        // Go through all children nodes and add the key
        for (TrieNode<List<String>> child : node.getChildrenMap().values()){
            addCandidates(child, prefix + child.getKey(), result);
        }
    }
}


