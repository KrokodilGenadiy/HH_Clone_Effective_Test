package com.example.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.core.ui.R
import com.example.data.adapter_interface.DelegateAdapterItem
import com.example.data.entities.ApiResponse
import com.example.data.entities.ConfirmButton
import com.example.data.entities.Vacancy
import com.example.ui.base_delegate_adapter.MainCompositeAdapter
import com.example.ui.databinding.FragmentFavoritesBinding
import com.example.ui.vacancy_adapter.VacancyAdapterDelegate
import com.example.ui.vacancy_adapter.VacancyItemDecorator
import com.example.utils.getPluralAddition
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<FavoritesViewModel>()
    private val vacancyList = mutableListOf<Vacancy>()
    private val mainAdapter by lazy {
        MainCompositeAdapter.Builder()
            .add(
                VacancyAdapterDelegate({
                    findNavController().navigate(
                        R.id.action_favoritesFragment_to_detailsFragment,
                        bundleOf(ARGS_VACANCY to it)
                    )
                },
                    {
                        triggerFavorites(it)
                    })
            )
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpVacancyAdapter()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.vacanciesFlow.collectLatest { vacancies: List<Vacancy> ->
                    viewModel.getData()
                    binding.numberOfVacancies.text =
                        "${vacancies.size} " + vacancies.size.getPluralAddition(
                            "вакансий",
                            "вакансии",
                            "вакансия"
                        )
                    mainAdapter.submitList(vacancies)
                    vacancyList.clear()
                    vacancyList.addAll(vacancies)
                }
            }
        }
    }

    private fun setUpVacancyAdapter() {
        binding.favoritesVacancyRecycler.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(FavoritesDecorator(8))
        }
    }

    private fun triggerFavorites(vacancy: Vacancy) {
        viewModel.deleteFromFavorites(vacancy)
        vacancyList.remove(vacancy)
        mainAdapter.submitList(vacancyList.toList())
        binding.numberOfVacancies.text =
            "${vacancyList.size} " + vacancyList.size.getPluralAddition(
                "вакансий",
                "вакансии",
                "вакансия"
            )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val ARGS_VACANCY = "vacancy"
    }

}