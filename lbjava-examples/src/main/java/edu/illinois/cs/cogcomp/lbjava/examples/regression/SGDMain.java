package edu.illinois.cs.cogcomp.lbjava.examples.regression;

import edu.illinois.cs.cogcomp.lbjava.classify.Classifier;
import edu.illinois.cs.cogcomp.lbjava.classify.TestReal;
import edu.illinois.cs.cogcomp.lbjava.learn.BatchTrainer;
import edu.illinois.cs.cogcomp.lbjava.learn.Learner;
import edu.illinois.cs.cogcomp.lbjava.learn.StochasticGradientDescent;

/**
 * This is the main function to programatically invoke the <code>SGDClassifier</code>
 *
 * @author Yiming Jiang
 */
public class SGDMain {
    public static void main(String[] args) {
        /* read the training data set */
        MyDataReader train = new MyDataReader(System.getProperty("user.dir")+"/data/regression/train.txt");

        /* programatically create SGDClassifier and set the learning rate */
        StochasticGradientDescent learner = new SGDClassifier();
        StochasticGradientDescent.Parameters p = new StochasticGradientDescent.Parameters();
        p.learningRate = Math.pow(10, -11);
        learner.setParameters(p);

        /* create a batch trainer and train for 1000 iterations */
        BatchTrainer trainer = new BatchTrainer(learner, train);
        trainer.train(1000);

        /* read the testing data set */
        MyDataReader test = new MyDataReader(System.getProperty("user.dir")+"/data/regression/test.txt");

        /* test the testing data set against gold */
        Classifier oracle = new MyLabel();
        TestReal.testReal(learner, oracle, test, true);
    }
}
