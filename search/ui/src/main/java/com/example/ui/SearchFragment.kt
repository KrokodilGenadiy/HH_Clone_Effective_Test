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
import com.example.data.entities.Vacancy
import com.example.ui.rv_adapters.delegate_adapters.ButtonAdapterDelegate
import com.example.ui.base_delegate_adapter.MainCompositeAdapter
import com.example.ui.rv_adapters.standart_adapters.OfferAdapter
import com.example.ui.rv_adapters.item_decorators.OffersItemDecorator
import com.example.data.adapter_interface.DelegateAdapterItem
import com.example.data.entities.ApiResponse
import com.example.data.entities.ConfirmButton
import com.example.ui.databinding.FragmentSearchBinding
import com.example.ui.vacancy_adapter.VacancyAdapterDelegate
import com.example.ui.vacancy_adapter.VacancyItemDecorator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<SearchViewModel>()
    private val offerAdapter by lazy {
        OfferAdapter()
    }
    private var vacancies_list = emptyList<Vacancy>()
    private val mainAdapter by lazy {
        MainCompositeAdapter.Builder()
            .add(
                VacancyAdapterDelegate({
                    findNavController().navigate(
                        R.id.action_searchFragment_to_detailsFragment,
                        bundleOf(ARGS_VACANCY to it)
                    )
                },
                    {
                        triggerFavorites(it)
                    })
            )
            .add(ButtonAdapterDelegate {
                showMoreClick()
            })
            .build()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpVacancyAdapter()
        setUpOfferAdapter()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.apiResponseFlow.collectLatest { response: ApiResponse ->
                    if (response.vacancies.isNotEmpty()) {
                        vacancies_list = response.vacancies
                        val list: MutableList<DelegateAdapterItem> =
                            vacancies_list.subList(0, 3).toMutableList()
                        list.add(ConfirmButton(response.vacancies.size - 3))
                        mainAdapter.submitList(list)
                    }
                    if (response.offers.isNotEmpty())
                        offerAdapter.submitList(response.offers)
                }
            }
        }
    }

    private fun setUpVacancyAdapter() {
        binding.vacancyRecycler.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(VacancyItemDecorator(8))
        }
    }

    private fun setUpOfferAdapter() {
        binding.offerRecycler.apply {
            adapter = offerAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(OffersItemDecorator(8))
        }
    }


    private fun showMoreClick() {
        mainAdapter.submitList(vacancies_list)
        with(binding) {
            offerRecycler.visibility = View.GONE
            vacancyForYouTitle.visibility = View.GONE
            sortVacanciesContainer.visibility = View.VISIBLE
        }
    }

    private fun triggerFavorites(vacancy: Vacancy) {
        if (vacancy.isFavorite) {
            viewModel.deleteFromFavorites(vacancy)
            vacancy.isFavorite = false
        } else {
            viewModel.addToFavorites(vacancy)
            vacancy.isFavorite = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val ARGS_VACANCY = "vacancy"
    }
}