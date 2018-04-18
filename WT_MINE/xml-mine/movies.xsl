<?xml version="1.0" encoding="UTF-8"?>
<html xsl:version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <body>
        <xsl:for-each select="collection/movie">
            <tr>
                <td><xsl:value-of select="title"/></td>
                <td><xsl:value-of select="duration"/></td>
                <td><xsl:value-of select="price"/></td>
            </tr>
        </xsl:for-each>
    </body>
</html>
