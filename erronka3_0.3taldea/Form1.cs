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
                Kontrol k = new Kontrol();

                Boolean loginEginDu = k.LoginEgin(txtNombre, txtPassword); 

                if (loginEginDu)
                {
                    MessageBox.Show("Logina ongi egina");

                    Logeatuta deitu = new Logeatuta();
                    deitu.Show(); // Corregido el error de mayúsculas en Show()
                    this.Hide();
                }
                else
                {
                    MessageBox.Show("Erabiltzailea edo Pasahitza ez dira zuzenak");
                    txtNombre.Clear();
                    txtPassword.Clear();
                }

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




