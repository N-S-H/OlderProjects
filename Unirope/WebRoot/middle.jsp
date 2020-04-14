<section id="about">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <!-- Start welcome area -->
          <div class="welcome-area">
            <div class="title-area">
          

<center>
<font color="red" size="4">
<b>

 <%
    String status=request.getParameter("status");
    if(status!=null)
    {
    	out.println(status);
    }
    %>
    </b>
    </font>
    </center>
    <br/>
              <h2 class="tittle">Welcome to <span>Control Plane</span></h2>
              <span class="tittle-line"></span>
              
            </div>
            <div class="welcome-content">
              <ul class="wc-table">
                <li>
                  <div class="single-wc-content wow fadeInUp">
                    <span class="fa fa-users wc-icon"></span>
                     <a href="flowanalysis.jsp"><h4 class="wc-tittle">Flow Analysis</h4></a>
                   <p>Flow Analysis</p>
                  </div>
                </li>
                <li>
                  <div class="single-wc-content wow fadeInUp">
                    <span class="fa fa-sellsy wc-icon"></span>
                   <a href="pathtraceanalysis.jsp">  <h4 class="wc-tittle">Path Trace</h4></a>
                    <p>Path Trace</p>
                  </div>
                </li>
                <li>
                  <div class="single-wc-content wow fadeInUp">
                    <span class="fa fa-line-chart wc-icon"></span>
                    <a href="packets.jsp"> <h4 class="wc-tittle">Packets</h4></a>
                    <p>Packets</p>
                  </div>
                </li>
                <li>
                  <div class="single-wc-content wow fadeInUp">
                    <span class="fa fa-bus wc-icon"></span>
                    <a href="setnodefail.jsp"> <h4 class="wc-tittle">Node Failed</h4></a>
                    <p>---------------------------------------------set node failed--------------------------------------------------------------------------</p>
                  </div>
                </li>
              </ul>
            </div>
          </div>
          <!-- End welcome area -->
        </div>
      </div>
  
    </div>
  </section> 
  
  
  <%
  String user=request.getParameter("userId");
  if(user!=null)
  {
   %>
  
  <section id="team">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="team-area">
            <div class="title-area">
              <h2 class="tittle">Send File</h2>
              <span class="tittle-line"></span>
              
            </div>
            <!-- Start team content -->
            <div class="team-content">
            <form action="GetData" method="post" enctype="multipart/form-data">
             <input type="file" name="file" class="form-control"/>
             <br/>
             <button type="submit" data-text="SUBMIT" class="button button-default"><span>SUBMIT</span></button>
             </form>
             <br/>
             <%
             if(request.getParameter("data")!=null)
             {
             String data=request.getParameter("data");
             
            String packets=data.replace(' ',',');
             
             
             
             
             
          //   String packets=request.getParameter("data").replace(" ",";;");
             
              %>
              <br>
              <br>
              <form action="SendData" method="post"> 
                       <center> 
               <h2 class="tittle">Data</h2>
                <span class="tittle-line"></span>
            
             <textarea rows="5" cols="20" name="data" class="form-control"><%=data%></textarea>
            
          <br/>
			<h2 class="tittle">Packets</h2>
       
           <span class="tittle-line"></span>
     
             <textarea rows="5" cols="20" name="packets" class="form-control"><%=packets %></textarea>
            <br/>
            <h2 class="tittle">Destination Host</h2>
       
           <span class="tittle-line"></span>
                 </center>
                 <%
                 System.out.println(session.getAttribute("userId"));
                  %>
                 <input type="hidden" name="sender" value="<%=(String)session.getAttribute("userId") %>"/>
            <select name="receiver" class="form-control">
            <option>A</option>
            <option>B</option>
            <option>C</option>
            <option>D</option>
            <option>E</option>
            <option>F</option>
            <option>G</option>
            </select>
            <br/>
              <button type="submit" data-text="SEND" class="button button-default"><span>SEND</span></button>
            </form>
            <%
            }
             %>
            </div>
            
            <!-- End team content -->
          </div>
        </div>
      </div>
    </div>
  </section>
  <%
  }
   %>
 

  
  <!-- Start Contact section -->
  <section id="contact">
    <div class="container">
      <div class="row">
        <div class="col-md-4 col-sm-6 col-xs-12">
          <div class="contact-left wow fadeInLeft">
            <h2>Contact with us</h2>
            <address class="single-address">
              <h4>Postal address:</h4>
              <p>xxxxxxxxxxxxxxxxxxx</p>
            </address>
             <address class="single-address">
              <h4>Headquarters:</h4>
              <p>xxxxxxxxxxxxxxxxxxxxxxxxx</p>
            </address>
             <address class="single-address">
              <h4>Phone</h4>
              <p>Phone Number: (+91) xxxxxx</p>
              <p>Fax Number: xxx xxxx</p>
            </address>
             <address class="single-address">
              <h4>E-Mail</h4>
              <p>Support: Support@example.com</p>
              <p>Sales: sales@example.com</p>
            </address>
          </div>
        </div>
        <div class="col-md-8 col-sm-6 col-xs-12">
          <div class="contact-right wow fadeInRight">
            <h2>Login here</h2>
            <form action="LoginController" class="contact-form">
              <div class="form-group">                
                <input type="text" class="form-control" placeholder="Name" name="userId">
              </div>
              <div class="form-group">                
                <input type="password" class="form-control" placeholder="Password" name="password">
              </div>              
              <!--<div class="form-group">
                <textarea class="form-control"></textarea>
              </div>
              --><button type="submit" data-text="SUBMIT" class="button button-default"><span>SUBMIT</span></button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </section>
  
  <!-- End Google Map -->
