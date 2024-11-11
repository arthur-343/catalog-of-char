import React, { useState, useEffect } from 'react';
import AppBar from '@mui/material/AppBar';
import Button from '@mui/material/Button';
import CameraIcon from '@mui/icons-material/PhotoCamera';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import CssBaseline from '@mui/material/CssBaseline';
import Box from '@mui/material/Box'; // substituindo Grid por Box
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Link from '@mui/material/Link';
import api from "../services/api"; // Importando a instância da API para fazer as requisições

function Copyright() {
  return (
    <Typography variant="body2" color="textSecondary" align="center">
      {'Copyright © '}
      <Link color="inherit" href="https://mui.com/">
        Your Website
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

export default function Album() {
  const [characters, setCharacters] = useState([]);

  useEffect(() => {
    // Requisição para obter os personagens da API
    api.get("/characterMain")  // Assumindo que o endpoint seja "/characterMain"
      .then((response) => {
        setCharacters(response.data);  // Atualiza o estado com os dados da resposta
      })
      .catch((error) => {
        console.error("Error fetching data: ", error);
      });
  }, []);  // Array vazio para rodar uma vez na montagem do componente

  return (
    <React.Fragment>
      <CssBaseline />
      <AppBar position="relative">
        <Toolbar>
          <CameraIcon sx={{ mr: 2 }} />
          <Typography variant="h6" color="inherit" noWrap>
            Character-Catalog
          </Typography>
        </Toolbar>
      </AppBar>
      <main>
        {/* Hero unit */}
        <Box sx={{ backgroundColor: '#f5f5f5', py: 8 }}>
          <Container maxWidth="sm">
            <Typography component="h1" variant="h2" align="center" color="textPrimary" gutterBottom>
              Character-Catalog
            </Typography>
            <Typography variant="h5" align="center" color="textSecondary">
              Explore a collection of characters from various series.
            </Typography>
            <Box sx={{ mt: 4, display: 'flex', justifyContent: 'center', gap: 2 }}>
              <Button variant="contained" color="primary">
                Main call to action
              </Button>
              <Button variant="outlined" color="primary">
                Secondary action
              </Button>
            </Box>
          </Container>
        </Box>

        <Container sx={{ py: 8 }} maxWidth="md">
          <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 4 }}>
            {characters.map((character) => (
              <Card key={character.id} sx={{ width: '100%', maxWidth: 345, display: 'flex', flexDirection: 'column' }}>
                <CardMedia
                  sx={{ pt: '56.25%' }}
                  image="https://source.unsplash.com/random?sig=1"  // Substitua por uma imagem real se necessário
                  title={character.name}
                />
                <CardContent sx={{ flexGrow: 1 }}>
                  <Typography gutterBottom variant="h5" component="h2">
                    {character.name}
                  </Typography>
                  <Typography><strong>Power:</strong> {character.power}</Typography>
                  <Typography><strong>Occupation:</strong> {character.occupation}</Typography>
                  <Typography><strong>Origin:</strong> {character.origin}</Typography>
                  <Typography><strong>Series:</strong> {character.series}</Typography>
                  <Typography><strong>Age:</strong> {character.age}</Typography>
                </CardContent>
                <CardActions>
                  <Button size="small" color="primary">View</Button>
                  <Button size="small" color="primary">Edit</Button>
                </CardActions>
              </Card>
            ))}
          </Box>
        </Container>
      </main>

      {/* Footer */}
      <footer style={{ backgroundColor: '#f5f5f5', padding: '48px 0' }}>
        <Typography variant="h6" align="center" gutterBottom>
          Footer
        </Typography>
        <Typography variant="subtitle1" align="center" color="textSecondary" component="p">
          Something here to give the footer a purpose!
        </Typography>
        <Copyright />
      </footer>
      {/* End footer */}
    </React.Fragment>
  );
}
