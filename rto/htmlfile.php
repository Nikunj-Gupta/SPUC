   <!doctype html>
    <html lang="en">
    <head>
      <meta charset="UTF-8">
      <title>Vehicle Details</title>
      <style>
        table, th, td {
         border: 1px solid black;
        border-collapse: collapse;
        }
      </style>
    </head>
    <body>
      <img src="https://s-media-cache-ak0.pinimg.com/originals/fa/f7/f3/faf7f3e1f8b4f7c1127cdbf46ddcfe38.jpg" alt="SPUC image" style="width:128px;height:128px;">
      <?php
      $temp1= new DateTime(date('Y-m-d'));
      $temp=$temp1->format('Y-m-d');
      #echo $temp;
      require_once('dbConnect.php');
      //execute the SQL query and return records
      $sql = "SELECT vehicle_id,sensor_id,owner_name,address FROM vehicles ";
      $sql2 = "SELECT vehicle_id,owner_name,address,co_percent FROM vehicles ";
      #$sql3 ="SELECT vehicle_id,owner_name,address FROM vehicles WHERE deadline_date < $temp";
      $sql3 ="SELECT vehicle_id,owner_name,address FROM vehicles WHERE deadline_date < NOW()";

      $result2= mysqli_query($conn,$sql2);
      $result = mysqli_query($conn,$sql);
      $result3= mysqli_query($conn,$sql3);
      ?>
      <h2 align="center"> ALL VEHICLES THAT HAVE BEEN REGISTERED </h2>
      <table border="2" style= "background-color: #f1f1c1; margin: 0 auto;" >
      <thead>
        <tr>
          <th>vehicle_id</th>
          <th>sensor_id</th>
          <th>owner_name</th>
          <th>Address</th>
        </tr>
      </thead>
      <tbody>
        <?php
          while( $row = mysqli_fetch_array( $result, MYSQL_ASSOC ) ){
            echo
            "<tr>
              <td>{$row['vehicle_id']}</td>
              <td>{$row['sensor_id']}</td>
              <td>{$row['owner_name']}</td>
              <td>{$row['address']}</td>
            </tr>\n";
          }
        ?>
      </tbody>
    </table>

<h2 align="center">ALL VEHICLES THAT HAVE CROSSED THE THRESHOLD VALUE</h2>
<table border="2" style= "background-color: #ffffff; margin: 0 auto;" >
      <thead>
        <tr>
          <th>Vehicle ID</th>
          <th>Name of the Owner</th>
          <th>Address</th>
          <th>co_percent</th>
        </tr>
      </thead>
      <tbody>
        <?php
          while( $row2 = mysqli_fetch_array( $result2 , MYSQL_ASSOC ) ){
            echo
            "<tr>
              <td>{$row2['vehicle_id']}</td>
              <td>{$row2['owner_name']}</td>
              <td>{$row2['address']}</td>
              <td>{$row2['co_percent']}</td>
            </tr>\n";
          }
        ?>
      </tbody>
    </table>

<h2 align="center"> ALL VEHICLES THAT HAVE CROSSED THE DEADLINE</h2>
<table border="2" style= "background-color: #ffffff; margin: 0 auto;" >
      <thead>
        <tr>
          <th>Vehicle ID</th>
          <th>Name of the Owner</th>
          <th>Address</th>
        </tr>
      </thead>
      <tbody>
        <?php
          while( $row3 = mysqli_fetch_array( $result3 , MYSQL_ASSOC ) ){
            echo
            "<tr>
              <td>{$row3['vehicle_id']}</td>
              <td>{$row3['owner_name']}</td>
              <td>{$row3['address']}</td>
            </tr>\n";
          }
        ?>
      </tbody>
    </table>



    </body>
    </html>