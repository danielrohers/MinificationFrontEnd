MinificationFrontEnd
====================

Minification Front-End is a plugin for the minification of Javascript or CSS

# Installation
#### In plugin:

    cd MinificationFrontEnd
    grails clean
    grails refresh-dependencies
    grails compile
    grails maven-install
    
#### In your project:

    cd YourProject

Add in `BuildConfig.groovy`:
    
    compile ":minification-front-end:0.1"

Run in terminal:

    grails clean
    grails refresh-dependencies
    grails compile
    
# Usage
In your project, run scripts when you want to minification:

    grails Minification js
    grails Minification css
