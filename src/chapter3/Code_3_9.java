package chapter3;

/**
 * Created by nanca on 11/4/2017.
 * 基本类型的局部变量与引用变量的线程封闭性
 */
public class Code_3_9 {

    public int loadTheArk(Collection<Animal> candidates) {
        SortedSet<Animal> animals;
        int numPairs = 0;
        Animal candidate = null;

        // animals 被封闭在方法中，不要使它们移除！
        animals = new TreeSet<Animal>(new SpeciesGenderComparator());
        animals.addAll(candidates);

        for (Animal a : animals) {
            if (candidate == null || !candidate.isPotentialMate(a)) {
                cadidate = a;
            } else {
                ark.load(new AnimalPair(cadidate, a));
                ++numPairs;
                candidate = null;
            }
        }
    }
}
