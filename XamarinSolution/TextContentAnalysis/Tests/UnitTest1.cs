using System;
using TextContentAnalysis;
using Xunit;

namespace Tests
{
    public class UnitTest1
    {
        [Fact]
        public void Test1()
        {
            TextContentAnalyzer textContentAnalyzer = new TextContentAnalyzer();
            var results = textContentAnalyzer.GetWordFrequency("one, two, two, three, three, three");
            Assert.Equal(1, results["one"]);
            Assert.Equal(2, results["two"]);
            Assert.Equal(3, results["three"]);
        }
    }
}
