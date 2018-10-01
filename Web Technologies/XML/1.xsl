<?xml version="1.0" encoding="UTF-8"?>
<html xsl:version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<body style="font-family:Arial;font-size:16pt;background-color:Azure">
<xsl:for-each select="breakfast_menu/food">
  <div style="background-color:AntiqueWhite;color:black;padding:4px;width:100%;text-align:center">
    <xsl:value-of select="name"/> - 
    <xsl:value-of select="price"/>
    </div>
  <div style="margin-left:20px;margin-bottom:1em;font-size:10pt">
    <p>
    <xsl:value-of select="description"/>
    <font style="font-style:italic"> (<xsl:value-of select="calories"/> calories per serving)</font>
    </p>
  </div>
</xsl:for-each>
</body>
</html> 
