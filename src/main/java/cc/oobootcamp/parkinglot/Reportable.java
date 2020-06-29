package cc.oobootcamp.parkinglot;

import java.util.List;

public interface Reportable {
    String report(int level);

    List<Reportable> getSubReportables();

    static String generateReport(Reportable reportable, int level, String content) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < level; i++) {
            result.append("\t");
        }
        result.append(content);

        for (Reportable subReportable : reportable.getSubReportables()) {
            result.append(subReportable.report(level + 1));
        }
        return result.toString();
    }

}
