<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0">
    <xsl:template match="/gym">
        <html>
            <head>
                <title>GYM</title>
                <link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet"> </link>
                <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet"> </link>
                <style>
                    body {
                        background-color: #F2F1EF;
                        text-align: center;
                    }
                        
                    h1 {
                        color: #D91E18;
                        font-family: 'Oswald', sans-serif;
                    }
                    
                    table {
                        border-collapse: collapse;
                    }
                    
                    th, td {
                        padding: 8px;
                        text-align: center;
                        border-bottom: 1px solid #abb7b7;
                        font-family: 'Roboto', sans-serif;
                    }
                    
                    p{
                        font-family: 'Roboto', sans-serif;
                    }
                    
                    #incomplete{
                        color: #CF000F;
                    }
                </style>
            </head>
            <body>
                <h1>MEMBERS WHO TAKE A YOGA COURSE</h1>
                <table align='center'>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Date Of Birth</th>
                        <th>Yoga Courses</th>
                    </tr>
                    <xsl:for-each select="//member">
                        <xsl:if test="contains(./prog/progID, 'YOG')">
                            <tr>
                                
                            <td>
                                <xsl:value-of select="@memberid"/>
                                <xsl:text></xsl:text> 
                            </td>
                            
                            <td>
                                <xsl:value-of select="./firstname/text()"/>
                                &#160;
                                <xsl:value-of select="./lastname/text()"/>
                            </td>
                                
                            <td>
                                <xsl:value-of select="./dateofbirth/text()"/>
                             </td>
                                
                            <td>
                                <xsl:for-each select="./prog">
                                    <xsl:if test="contains(./progID, 'YOG')">
                                        <xsl:choose>
                                            <xsl:when test="./completed='No'">
                                                <span id='incomplete'>&#160;<xsl:value-of select="./progID/text()"/></span>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                &#160;<xsl:value-of select="./progID/text()"/>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                           
                                    </xsl:if>
                                </xsl:for-each>
                            </td>
                            </tr>
                        </xsl:if>
                    </xsl:for-each>
                </table>
                <p>NUMBER OF MEMBERS: <xsl:value-of select="count(//member[contains(prog/progID, 'YOG')])"/>/<xsl:value-of select="count(//member)"/><br><span id='incomplete'>Red</span> courses are incomplete.</br></p>
            </body>
        </html>
        
    </xsl:template>
</xsl:stylesheet>