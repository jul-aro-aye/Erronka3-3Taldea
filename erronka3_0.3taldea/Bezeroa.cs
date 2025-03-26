using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace erronka3_0._3taldea
{
    internal class Bezeroa
    {
        public int Id { get; set; }


        public static Boolean loginEginDezake(String erab, String pass)
        {
            // Obtener la conexión
            MySqlConnection conexionDB = Conexioa.conexion();

            // Verificar si la conexión es válida
            if (conexionDB == null)
            {
                MessageBox.Show("Errorea datu basearekin konektatzean.");
                return false;
            }

            // Verifica si la conexión está cerrada antes de abrirla
            if (conexionDB.State == System.Data.ConnectionState.Closed)
            {
                conexionDB.Open();
            }

            // Crear el comando SQL con parámetros
            MySqlCommand codigo = new MySqlCommand
            {
                Connection = conexionDB,
                CommandText = "SELECT * FROM bezeroa WHERE erabiltzailea = @erabiltzailea AND pasahitza = @pasahitza"
            };
            codigo.Parameters.AddWithValue("@erabiltzailea", erab);
            codigo.Parameters.AddWithValue("@pasahitza", pass);

            // Ejecutar la consulta
            MySqlDataReader leer = codigo.ExecuteReader();

            Boolean loginEginDu = leer.Read();

            // Cerrar el lector y la conexión
            leer.Close();
            conexionDB.Close();

            return loginEginDu;
        }
    }
}
