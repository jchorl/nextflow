package nextflow.splitter
import java.nio.file.Files
import java.nio.file.Path

import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors
/**
 * Base class for splitter handling binary data
 *
 * @author Paolo Di Tommaso <paolo.ditommaso@gmail.com>
 */

@CompileStatic
@InheritConstructors
abstract class AbstractBinarySplitter extends AbstractSplitter<InputStream> {


    protected InputStream normalizeType( obj ) {

        if( obj instanceof InputStream )
            return (InputStream) obj

        if( obj instanceof byte[] )
            return new ByteArrayInputStream(obj)

        if( obj instanceof CharSequence )
            return new ByteArrayInputStream(obj.toString().bytes)

        if( obj instanceof Path )
            return Files.newInputStream(obj)

        if( obj instanceof File )
            return new FileInputStream(obj)

        throw new IllegalAccessException("Object of class '${obj.class.name}' does not support 'chopBytes' method")

    }


}
