using System;
using System.Collections.Generic;
using System.Text;

namespace TextContentAnalysis
{
    public class TextContentAnalyzer
    {

        Dictionary<string, int> WordFrequencyDictionary = new Dictionary<string, int>();

        public Dictionary<string, int> GetWordFrequency(string textBody)
        {
            WordFrequencyDictionary.Clear();
            BreakTextIntoWords(textBody);
            return WordFrequencyDictionary;
        }

        private void BreakTextIntoWords(string input)
        {
            StringBuilder stringBuilder = new StringBuilder();
            foreach (char c in input)
            {
                if (IsCharicterPartOfWord(c))
                {
                    stringBuilder.Append(c);
                }
                else
                {
                    CountWord(stringBuilder.ToString());
                    stringBuilder.Clear();
                }
            }
            CountWord(stringBuilder.ToString());
        }

        private bool IsCharicterPartOfWord(char c)
        {
            return Char.IsLetter(c);
        }

        private void CountWord(string word)
        {
            if (word == null || word.Equals(""))
            {
                return;
            }
            var wordAsLower = word.ToLower();
            var count = 1;
            if (WordFrequencyDictionary.ContainsKey(wordAsLower))
            {
                count = WordFrequencyDictionary[wordAsLower] + 1;
            }
            WordFrequencyDictionary[wordAsLower] = count;
        }

    }
}
