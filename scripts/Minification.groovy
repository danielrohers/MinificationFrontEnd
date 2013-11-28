includeTargets << grailsScript("_GrailsInit")

target(main: " Minification Javascript or CSS ") {
    String yuiCompressorPath = null
    String extension = args ?: null
    File file = new File('lib')
    Boolean valid = true
    if(!extension || !['js', 'css'].contains(extension)) {
        valid = false
        println "Extension not equals 'js' or 'css'"
        println "Example: 'grails Minification js' or 'grails Minification css' "
    }

    if (valid) {
        file.eachFileRecurse {
            String path = it.absolutePath
            if(endsWith(['yuicompressor-2.3.6.jar'], path)) {
                yuiCompressorPath = path
            }
        }

        if (!yuiCompressorPath) {
            valid = false
            println "Can not find the jar: yuicompressor-2.3.6"
        }
    }

    if(valid) {
        println "Initialized minification"

        file = new File("web-app/${extension}")
        println "Extension: ${extension}"
        println "Path JS: ${file.absolutePath}"
        file.eachFileRecurse {
            executeYuiCompressor(yuiCompressorPath, extension, it.absolutePath)
        }

        println "Finalized minification"
    }
}

/**
 *
 * @param yuiCompressorPath
 * @param filePath
 */
private void executeYuiCompressor(String yuiCompressorPath, String extension, String filePath){
    if(endsWith([".${extension}"], filePath) && !endsWith([".min.${extension}"], filePath)) {
        "java -jar ${yuiCompressorPath} ${filePath} -o ${filePath} --charset utf-8".execute()
        println "java -jar ${yuiCompressorPath} ${filePath} -o ${filePath} --charset utf-8\n\n"
    }
}

/**
 *
 * @param extensions
 * @param filePath
 * @return
 */
private Boolean endsWith(List extensions, String filePath){
    if(extensions){
        for(String ext : extensions){
            if(filePath.endsWith(ext)){ return true }
        }
    }
    return false
}

setDefaultTarget(main)