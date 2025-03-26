using MySql.Data.MySqlClient;

using MySql.Data.MySqlClient;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace erronka3_0._3taldea
{
    class Conexioa

    {
       

      
            public static MySqlConnection conexion()
            {
                string servidor = "localhost";
                string puerto = "3306";
                string bd = "erronka3taldea";
                string usuario = "root";
                string password = "1MG2024";

                string cadenaConexion = "Database=" + bd + "; Data Source=" + servidor + "; Port=" + puerto + ";User Id=" + usuario + "; Password=" + password + "";

            try
                {
                    MySqlConnection conexionDB = new MySqlConnection(cadenaConexion);
                    return conexionDB;
                }
                catch (MySqlException ex)
                {
                    Console.WriteLine("Error: " + ex.Message);
                }

                return null;
            }
        }
    }




