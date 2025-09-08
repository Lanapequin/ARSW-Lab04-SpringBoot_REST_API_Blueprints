package edu.eci.arsw.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class GrammarChecker {
	private final SpellChecker sc;
	String x;

    @Autowired
    public GrammarChecker(@Qualifier("englishSpellChecker") SpellChecker sc) {
        this.sc = sc;
    }
        
	public SpellChecker getSpellChecker() {
		return sc;
	}

	public String check(String text){
        return "Spell checking output:" + sc.checkSpell(text) +
                "Plagiarism checking output: Not available yet";
	}
}
