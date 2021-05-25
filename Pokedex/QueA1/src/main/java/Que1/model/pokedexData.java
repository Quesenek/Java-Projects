package Que1.model;

import java.util.ArrayList;

import com.github.oscar0812.pokeapi.models.games.PokemonEntry;
import com.github.oscar0812.pokeapi.models.pokemon.Pokemon;
import com.github.oscar0812.pokeapi.models.pokemon.PokemonAbility;
import com.github.oscar0812.pokeapi.models.pokemon.PokemonForm;
import com.github.oscar0812.pokeapi.models.pokemon.PokemonStat;
import com.github.oscar0812.pokeapi.models.pokemon.PokemonType;
import com.github.oscar0812.pokeapi.utils.Client;

public class pokedexData 
{
    private int pokemonAmount = 893;
    private ArrayList<Pokemon> poke;
    private ArrayList<String> pokeNames;
    

    public pokedexData()
    {
        int getPokemon = 5;
        //Pokemon fPoke = Client.getPokemonById(1);
        poke = new ArrayList<Pokemon>();
        pokeNames = new ArrayList<String>();
        populatePokemon();
        
        String pokeList = "";


        
        //pokeList += String.format("ID: %s\nName: %s\nHeight: %s' %s\"\nWeight: %.1f lbs\n", poke.get(getPokemon).getId(), poke.get(getPokemon).getName(), pokeHeightFeet, pokeHeightInches, hectogramToPounds);

        System.out.println(pokeList);
        for (PokemonType pokemons : poke.get(48).getTypes()) 
        {
            System.out.println(pokemons.getType().getName());    
        }
        
    }

    private void populatePokemon()
    {
        for (int i = 0; i < pokemonAmount; i++) 
        {
            this.poke.add(Client.getPokemonById(i+1));
        }
        for (Pokemon pokem : poke) 
        {
            pokeNames.add(pokem.getName());
        }
    }

    public Boolean CheckValid(String name)
    {
        return pokeNames.contains(name);
    }

    public String[][] getPokemonData(String name)
    {
        String[][] compiledData = new String[10][3];
        int indexOfPokemon = pokeNames.indexOf(name);

        // Translate the data from the Api into Inches and pounds.
        double decimetersToInches = Math.round(poke.get(indexOfPokemon).getHeight() * 3.937);
        int pokeHeightFeet = (int)decimetersToInches / 12;
        int pokeHeightInches = (int)decimetersToInches % 12;

        double hectogramToPounds = Math.round(poke.get(indexOfPokemon).getWeight() * 0.22046);

        // Add data to an array to send to the view controller.
        compiledData[0][0] = String.format(".%s", poke.get(indexOfPokemon).getId()); //ID
        compiledData[1][0] = String.format("%s%s", poke.get(indexOfPokemon).getName().substring(0, 1).toUpperCase(), poke.get(indexOfPokemon).getName().substring(1)); //Name

        // Get the types of the pokemon.
        int counter = 0;
        for (PokemonType pokemons : poke.get(indexOfPokemon).getTypes()) 
        {
            compiledData[2][counter] = String.format("%s", pokemons.getType().getName());
            counter++;
        }
        counter = 0;

        for (PokemonAbility pokemons : poke.get(indexOfPokemon).getAbilities()) 
        {
            compiledData[3][counter] = String.format("%s", pokemons.getAbility().getName());
            counter++;
        }
        counter = 0;
        

        return compiledData;
    }
}
