using MySql.Data.MySqlClient;
using System;
using System.Windows.Forms;

namespace erronka3_0._3taldea
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

 
        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void textBox2_TextChanged(object sender, EventArgs e)
        {

        }

        private void btnlogin_Click(object sender, EventArgs e)
        {
            try
            {
                // Obtener la conexión
                MySqlConnection conexionDB = Conexioa.conexion();

                // Verificar si la conexión es válida
                if (conexionDB == null)
                {
                    MessageBox.Show("Error al conectar con la base de datos.");
                    return;
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
                codigo.Parameters.AddWithValue("@erabiltzailea", txtNombre.Text);
                codigo.Parameters.AddWithValue("@pasahitza", txtPassword.Text);

                // Ejecutar la consulta
                MySqlDataReader leer = codigo.ExecuteReader();

                if (leer.Read())
                {
                    MessageBox.Show("Login exitoso");

                    Logeatuta deitu = new Logeatuta();
                    deitu.Show(); // Corregido el error de mayúsculas en Show()
                    this.Hide();
                }
                else
                {
                    MessageBox.Show("Usuario o contraseña incorrectos");
                    txtNombre.Clear();
                    txtPassword.Clear();
                }

                // Cerrar el lector y la conexión
                leer.Close();
                conexionDB.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error: " + ex.Message);
            }
        }


        private void btnAnotherAction1_Click(object sender, EventArgs e)
        {
            foreach (Control control in this.Controls)
            {
                if (control is Label label)
                {
                    label.Text = string.Empty;
                }
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {

        }
    }
}




