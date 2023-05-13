package nextflow.fus3

import groovy.transform.CompileStatic
import java.nio.file.Path
import nextflow.cloud.aws.batch.AwsBatchExecutor
import nextflow.cloud.aws.batch.AwsBatchTaskHandler
import nextflow.cloud.aws.batch.AwsBatchTaskHandler
import nextflow.executor.BashWrapperBuilder
import nextflow.executor.SimpleFileCopyStrategy
import nextflow.plugin.BasePlugin
import nextflow.processor.TaskHandler
import nextflow.processor.TaskRun

import groovy.util.logging.Slf4j
import nextflow.util.ServiceName
import org.pf4j.ExtensionPoint
import org.pf4j.PluginWrapper

@Slf4j
@CompileStatic
class Fus3FileCopyStrategy extends SimpleFileCopyStrategy {
    @Override
    String getStageInputFilesScript(Map<String,Path> inputFiles) {
        println("JOSH getStageInputFilesScript $inputFiles")
        super.getStageInputFilesScript(inputFiles)
    }

    @Override
    protected String stageInCommand( String source, String target, String mode ) {
        println("JOSH stageInCommand $source, $target, $mode")
        super.stageInCommand(source, target, mode)
    }
}

class AwsBatchFus3TaskHandler extends AwsBatchTaskHandler {
    AwsBatchFus3TaskHandler(TaskRun task, AwsBatchExecutor executor) {
        super(task, executor)
    }

    @Override
    protected void buildTaskWrapper() {
        println("JOSH buildTaskWrapper")
        (new BashWrapperBuilder(task.toTaskBean(), new Fus3FileCopyStrategy())).build()
    }
}

@Slf4j
@ServiceName('awsbatchfus3')
@CompileStatic
class AwsBatchFus3Executor extends AwsBatchExecutor implements ExtensionPoint {
    @Override
    TaskHandler createTaskHandler(TaskRun task) {
        return new AwsBatchFus3TaskHandler(task,this)
    }
}

@CompileStatic
class Fus3Plugin extends BasePlugin {
    Fus3Plugin(PluginWrapper wrapper) {
        super(wrapper)
    }
}
