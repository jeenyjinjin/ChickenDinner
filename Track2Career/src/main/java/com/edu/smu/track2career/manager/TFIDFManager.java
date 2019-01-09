package com.edu.smu.track2career.manager;

import java.util.Arrays;
import java.util.List;

public class TFIDFManager {
    /**
     * @param doc  list of strings
     * @param term String represents a term
     * @return term frequency of term in document
     */
    public double tf(List<String> doc, String term) {
        if (term.contains(" ")) {
            List<String> terms = Arrays.asList(term.split(" "));
            return tfMul(doc, terms);
        }
        
        double result = 0;
        for (String word : doc) {
            if (term.equalsIgnoreCase(word))
                result++;
        }
        return result / doc.size();
    }
    
    /**
     * @param doc  list of strings
     * @param term list of strings represents a term
     * @return term frequency of term in document
     */
    public double tfMul(List<String> doc, List<String> term) {
        double result = 0;
        int count = term.size();
        int diff = count - 1;
        String first = term.get(0);
        System.out.println("first: " + first);
        for (int index = 0; index < doc.size(); index++) {
            String word = doc.get(index);
            System.out.println("current: " + word + ", first: " + first + " >> " + (first.equalsIgnoreCase(word)));
            if (first.equalsIgnoreCase(word)) {
                boolean hasFound = true;
                for (int inner = 1; inner <= count - 1; inner++) {
                    String next = doc.get(index + inner);
                    if (!term.get(inner).equalsIgnoreCase(next)) {
                        hasFound = false;
                        break;
                    }
                }
                if (hasFound) {
                    System.out.println("found");
                    result++;
                    index += count - 1;
                }
            }
        }
        System.out.println((doc.size() - (diff * result)));
        return result / (doc.size() - (diff * result));
    }
    
    /**
     * @param docs list of list of strings represents the dataset
     * @param term String represents a term
     * @return the inverse term frequency of term in documents
     */
    public double idf(List<List<String>> docs, String term) {
        if (term.contains(" ")) {
            List<String> terms = Arrays.asList(term.split(" "));
            return idfMul(docs, terms);
        }
        
        double n = 0;
        for (List<String> doc : docs) {
            for (String word : doc) {
                if (term.equalsIgnoreCase(word)) {
                    n++;
                    break;
                }
            }
        }
        return Math.log(docs.size() / n);
    }
    
    /**
     * @param docs list of list of strings represents the dataset
     * @param term list of strings represents a term
     * @return the inverse term frequency of term in documents
     */
    public double idfMul(List<List<String>> docs, List<String> term) {
        double n = 0;
        int count = term.size();
        String first = term.get(0);
        
        for (List<String> doc : docs) {
            for (int index = 0; index < doc.size(); index++) {
                String word = doc.get(index);
                if (first.equalsIgnoreCase(word)) {
                    boolean hasFound = true;
                    for (int inner = 1; inner <= count - 1; inner++) {
                        String next = doc.get(index + inner);
                        if (!term.get(inner).equalsIgnoreCase(next)) {
                            hasFound = false;
                            break;
                        }
                    }
                    if (hasFound) {
                        n++;
                        break;
                    }
                }
            }
        }
        return Math.log(docs.size() / n);
    }

    /**
     * @param doc  a text document
     * @param docs all documents
     * @param term term
     * @return the TF-IDF of term
     */
    public double tfIdf(List<String> doc, List<List<String>> docs, String term) {
        return tf(doc, term) * idf(docs, term);
    }
    
    public static void main(String[] args) {
        String document1 = "Identify a high level retail banking architecture, process and technology framework  .Differentiate business operation processes and detailed transaction flows in selected areas. Ascertain supporting technologies & typical IT solutions for banking service delivery channels with focus on mobile technology (cellular, IOT, mobile devices etc).Identify issues, considerations and tradeoffs for different banking solutions, e.g. internet banking and core banking. Identify relevant banking security and data privacy compliances. Identify emerging technologies and trends in the industry. Apply retail banking key concepts via an innovation project. Differentiate retail banking key concepts from other types of banks";
        String document2 = "Explain what money is, what payment domains are, and what payment systems are. Explain domestic and international payment instruments. Explain credit card; product setup, customer acquisition, and transaction fulfilment. Explain clearing & settlement mechanisms; ACH, RTGS, CLS. Explain payment processing silos vs. payment services hub architectures. Explain payment security hacks, payment fraud prevention and detection. Explain payment message standards, and payment industry standards. Perform interbank payments through a simulated ACH. Explain Payment Services Directive 2 (PSD2): Access to Account (XS2A). Explain Open API: Account Information Services, and Payment Initiation Services. Explain Marketplace Banking and the disrupting impact on traditional banks. Explain the rise of FinTech alternative payment services providers. Explain Mobile Payments and the financial inclusion of the unbanked. Explain Blockchain basics and Cryptocurrencies. Research and report on non-bank FinTech alternative payment services. Provide a solution for a selected payments problem identified by MAS.";
        String document3 = "Understand corporate customers and the financial services that they need. Become familiar with the basic technologies used in the major functional and business areas of corporate banking. Gain a depth of understanding on corporate banking core products and services such as; corporate lending, cash management, interbank payments, trade finance, supply chain financing, money market products, and foreign currency exchange. Understand the mechanics and impact of financial crisis, and the role of large financial institutions and central banks and the potential impact of blockchain on a future crisis. Design and implement Smart Contracts and understand their use cases in the areas of. Trade document de-materialization, settlement and over-the-counter derivatives such as credit default swaps.";
        
        document1 = document1.replaceAll("\\.", "");
        document2 = document2.replaceAll("\\.", "");
        document3 = document3.replaceAll("\\.", "");
        
        List<String> doc1 = Arrays.asList(document1.split(" "));
        List<String> doc2 = Arrays.asList(document2.split(" "));
        List<String> doc3 = Arrays.asList(document3.split(" "));
//        List<String> doc1 = Arrays.asList("Lorem", "ipsum", "dolor", "Lorem", "ipsum", "sit", "Lorem", "ipsum");
//        List<String> doc2 = Arrays.asList("Vituperata", "incorrupte", "at", "Lorem", "ipsum", "pro", "quo");
//        List<String> doc3 = Arrays.asList("Has", "persius", "disputationi", "id", "simul");
        List<List<String>> documents = Arrays.asList(doc1, doc2, doc3);

        TFIDFManager calculator = new TFIDFManager();
        double tfidf = calculator.tfIdf(doc1, documents, "Core banking");
        System.out.println("TF-IDF (ipsum) = " + tfidf);
    }
}
