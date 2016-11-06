package io.sealights.plugins.sealightsjenkins.buildsteps.commands.executors;

import io.sealights.plugins.sealightsjenkins.buildsteps.commands.entities.CommandModes;
import io.sealights.plugins.sealightsjenkins.buildsteps.commands.entities.UploadReportsCommandArguments;
import io.sealights.plugins.sealightsjenkins.utils.Logger;
import io.sealights.plugins.sealightsjenkins.utils.StringUtils;

import java.util.List;

/**
 * Created by shahar on 11/4/2016.
 */
public class UploadReportsCommandExecutor extends AbstractExecutor{

    private UploadReportsCommandArguments uploadReportsCommandArguments;

    public UploadReportsCommandExecutor(Logger logger, String agentPath, UploadReportsCommandArguments uploadReportsCommandArguments) {
        super(logger, agentPath, CommandModes.UploadReports, uploadReportsCommandArguments.getCommonCommandArguments());
        this.uploadReportsCommandArguments = uploadReportsCommandArguments;
    }

    @Override
    public String getAdditionalArguments() {
        StringBuilder sb = new StringBuilder();

        List<String> reportFiles = StringUtils.commaSeparatedToList(uploadReportsCommandArguments.getReportFiles());
        for (String reportFile: reportFiles){
            addArgumentKeyVal(sb, "reportFile", reportFile);
        }

        List<String> reportFilesFolders = StringUtils.commaSeparatedToList(uploadReportsCommandArguments.getReportsFolders());
        for (String reportFolder: reportFilesFolders){
            addArgumentKeyVal(sb, "reportFilesFolder", reportFolder);
        }

        addArgumentKeyVal(sb, "hasMoreRequests", String.valueOf(uploadReportsCommandArguments.isHasMoreRequests()));
        addArgumentKeyVal(sb, "source", String.valueOf(uploadReportsCommandArguments.getSource()));

        return sb.toString();
    }
}
